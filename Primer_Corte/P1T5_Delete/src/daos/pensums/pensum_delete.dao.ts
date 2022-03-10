import { Response } from 'express';
import { red } from 'colors';
import connectionDB from '../../config/connection/connection_DB';


export class PensumDAO_Delete {
    protected static deleteOnePensumById = async (sqlConfirmId: string, sqlDelete: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount } = await connectionDB.pool.one(sqlConfirmId, params)
            if (amount === '0') return res.status(400).json({ ok: false, msg: `No existe ningún pensum con el id ${params[0]}` })
            const { rowCount } = await connectionDB.pool.result(sqlDelete, params)
            return res.status(200).json({ 
                ok: true, 
                msg: `El pensum con el id ${params[0]}, ha sido eliminado`,
                affectedRows: rowCount,
                idPensum: params[0]
            })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}