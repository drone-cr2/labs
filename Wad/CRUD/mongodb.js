// file to connect/configure the database

const {MongoClient} = require('mongodb')
const url = "mongodb://localhost:27017/"
const client = new MongoClient(url) // make the client with the localhost url

const database = 'student'
const collection = 'profile'

//function that returns the connection
const dbConnect  = async ()=>{
    const result = await client.connect() // connect returns a promise, hence await
    const db = await result.db(database)
    return db.collection(collection)
}

module.exports = dbConnect

