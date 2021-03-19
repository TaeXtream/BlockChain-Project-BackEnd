'use strict';

const {Contract} = require('fabric-contract-api');

class FabProperty extends Contract {

    async InitLedger(ctx) {
        console.info('============= START : Initialize Ledger ===========');
        // const properties = [
        //     {ID:'p1', name:'J House', signature:'adsd', owner:'James', documentPath:'asset/doc/ti1.pdf', imagePath:'asset/image/p1.jpg'},
        //     {ID:'p2', name:'12Condo', signature:'4xes', owner:'Tomson', documentPath:'asset/doc/ti2.pdf', imagePath:'asset/image/p2.jpg'},
        //     {ID:'p3', name:'U Mansion', signature:'man123', owner:'Jack', documentPath:'asset/doc/ti2.pdf', imagePath:'asset/image/p3.jpg'},
        //     {ID:'p4', name:'Oha Island', signature:'is1s23', owner:'Tony', documentPath:'asset/doc/ti2.pdf', imagePath:'asset/image/p4.jpg'}

        // ];

        // for (const property of properties){
        //     await this.createProperty(ctx,property.ID,property.name,property.signature,property.owner,property.documentPath,property.imagePath);
        // }
        console.info('============= END : Initialize Ledger ===========');
    }

    async createProperty(ctx, id, name, signature, owner, documentPath, imagePath) {
        const exists = await this.propertyExists(ctx, id);
        if (exists) {
            throw new Error(`The Property ${id} already exists`);
        }
        let newProperty = {
            docType: 'property',
            ID: id,
            name: name,
            signature: signature,
            owner: owner,
            documentPath: documentPath,
            imagePath: imagePath
        };
        ctx.stub.putState(id, Buffer.from(JSON.stringify(newProperty)));
        console.info('Added <--> ', name);
        return JSON.stringify(newProperty);
    }

    async getPropertySignature(ctx, id) {
        const propertyString = await this.readProperty(ctx, id);
        const property = JSON.parse(propertyString);
        return property.signature;
    }

    async getPropertyOwner(ctx, id) {
        const propertyString = await this.readProperty(ctx, id);
        const property = JSON.parse(propertyString);
        return property.owner;
    }

    async getPropertyDocumentPath(ctx, id) {
        const propertyString = await this.readProperty(ctx, id);
        const property = JSON.parse(propertyString);
        return property.documentPath;
    }

    async getPropertyImagePath(ctx, id) {
        const propertyString = await this.readProperty(ctx, id);
        const property = JSON.parse(propertyString);
        return property.imagePath;
    }

    async readProperty(ctx, id) {
        const propertyJSON = await ctx.stub.getState(id);
        if (!propertyJSON || propertyJSON.length === 0) {
            throw new Error(`The Property ${id} does not exist`);
        }
        return propertyJSON.toString();
    }

    async propertyExists(ctx, id) {
        const assetJSON = await ctx.stub.getState(id);
        return assetJSON && assetJSON.length > 0;
    }

    async updateProperty(ctx, id, name, signature, owner) {
        const exists = await this.propertyExists(ctx, id);
        if (!exists) {
            throw new Error(`The Property ${id} does not exist`);
        }
        const updateProperty = {
            docType: 'property',
            ID: id,
            name: name,
            signature: signature,
            owner: owner
        };
        return ctx.stub.putState(id, Buffer.from(JSON.stringify(updateProperty)))
    }

    async deleteProperty(ctx, id) {
        const exists = await this.propertyExists(ctx, id);
        if (!exists) {
            throw new Error(`The Property ${id} does not exist`);
        }
        return ctx.stub.deleteState(id);
    }

    async getPropertyHistory(ctx, id) {
        let resultsIterator = await ctx.stub.getHistoryForKey(id);
        let results = await this.GetAllResults(resultsIterator, true);

        return JSON.stringify(results);
    }

    async GetAllResults(iterator, isHistory) {
        let allResults = [];
        let res = await iterator.next();
        while (!res.done) {
            if (res.value && res.value.value.toString()) {
                let jsonRes = {};
                console.log(res.value.value.toString('utf8'));
                if (isHistory && isHistory === true) {
                    jsonRes.TxId = res.value.tx_id;
                    jsonRes.Timestamp = res.value.timestamp;
                    try {
                        jsonRes.Value = JSON.parse(res.value.value.toString('utf8'));
                    } catch (err) {
                        console.log(err);
                        jsonRes.Value = res.value.value.toString('utf8');
                    }
                } else {
                    jsonRes.Key = res.value.key;
                    try {
                        jsonRes.Record = JSON.parse(res.value.value.toString('utf8'));
                    } catch (err) {
                        console.log(err);
                        jsonRes.Record = res.value.value.toString('utf8');
                    }
                }
                allResults.push(jsonRes);
            }
            res = await iterator.next();
        }
        iterator.close();
        return allResults;
    }

    async transferProperty(ctx, id, newOwner) {
        const propertyString = await this.readProperty(ctx, id);
        const property = JSON.parse(propertyString);
        property.owner = newOwner;
        return ctx.stub.putState(id, Buffer.from(JSON.stringify(property)));
    }

    async getAllProperties(ctx) {
        const allResults = [];
        const iterator = await ctx.stub.getStateByRange('', '');
        let result = await iterator.next();
        while (!result.done) {
            const strValue = Buffer.from(result.value.value.toString()).toString('utf8');
            let record;
            try {
                record = JSON.parse(strValue);
            } catch (err) {
                console.log(err);
                record = strValue;
            }
            allResults.push({Key: result.value.key, Record: record});
            result = await iterator.next();
        }
        return JSON.stringify(allResults);
    }


}

module.exports = FabProperty;
