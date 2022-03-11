import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../../config/connection/connection_DB';


export class AccessDAO_Get {
    protected static getAccess = async (sqlQuery: string, params: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, params)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}