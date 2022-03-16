import { Response } from 'express';
import { red } from 'colors';
import connectionDB from '../../config/connection/connection_DB';
import { AccessDAO_RES } from '.';


export class AccessDAO_Post {
    protected static validateAccess = async (sqlConfirmAccess: string, params: any, res: Response): Promise<any> => {
        try {
            const data = await connectionDB.pool.oneOrNone(sqlConfirmAccess, params)
            // if (data === null) return res.status(400).json({ ok: false, msg: `Error en el email o contraseña` })
            // return res.status(200).json({ ok: true, msg: 'Se ha encontrado una coincidencia', accessId: data.accessId })
            return AccessDAO_RES.process(data, res)
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }


    protected static postAccess = async (sqlConfirmUnique: string, sqlCreate: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount } = await connectionDB.pool.one(sqlConfirmUnique, params[0])
            if (amount !== '0') return res.status(400).json({ ok: false, msg: 'No se puede repetir emails de acceso' })
            const { accessId } = await connectionDB.pool.one(sqlCreate, params)
            return res.status(201).json({ ok: true, msg: `Se ha creado un nuevo acceso`, accessId })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    } 
}