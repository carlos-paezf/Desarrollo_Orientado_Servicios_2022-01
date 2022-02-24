'use strict'

import { red, blue } from 'colors'
import pgPromise from 'pg-promise'

import ConnectionOptions from './connection_options'
import var_db from '../domains/var_db.config'


class ConnectionDB {
    private _pgp = pgPromise(ConnectionOptions.pgOptions)
    public pool: any

    constructor() {
        this.pool = this._pgp(var_db)
        this.connectPool()
    }

    /**
     * A function that connects to the database. 
     */
    connectPool = async () => {
        try {
            const connection = await this.pool.connect()
            connection.done()
            console.log(blue.italic(`> Conexión establecida con la base de datos ${var_db.database}\n`))
        } catch (error) {
            console.log(red('Error in ConnectionDB: '), error)
        }
    }
}


const connectionDB = new ConnectionDB()
export default connectionDB