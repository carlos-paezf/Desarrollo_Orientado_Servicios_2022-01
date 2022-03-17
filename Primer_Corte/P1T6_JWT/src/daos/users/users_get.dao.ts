import { Response } from 'express';
import { red } from 'colors';
import connectionDB from '../../config/connection/connection_DB';


export class UserDAO_Get {
    protected static getUserAndLoginCountByID = async (sqlQuery: string, sqlCount: string, params: any, res: Response): Promise<any> => {
        try {
            const data = await connectionDB.pool.oneOrNone(sqlQuery, params)
            if (data === null) {
                return res.status(400).json({ ok: false, msg: 'No existe ningún registro con el id ingresado' })
            }
            const { amountLogin } = await connectionDB.pool.one(sqlCount, params)
            return res.status(200).json({ ok: true, data, amountLogin })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }

    protected static getAllUsers = async (sqlQuery: string, sqlCountLogin: string, params: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, params)
            const { allLogin } = await connectionDB.pool.one(sqlCountLogin, params)
            return res.status(200).json({ ok: true, allLogin, rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}