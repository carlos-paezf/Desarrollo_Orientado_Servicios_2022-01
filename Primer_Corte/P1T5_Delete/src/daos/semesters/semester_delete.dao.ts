import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../../config/connection/connection_DB'


export class SemesterDAO_Delete {
    protected static deleteOneSemesterById = async (sqlConfirmId: string, sqlDelete: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount } = await connectionDB.pool.one(sqlConfirmId, params)
            if (amount === '0') return res.status(400).json({ ok: false, msg: `No hay ningún semestres con el id ${params}` })
            const { rowCount } = await connectionDB.pool.result(sqlDelete, params)
            return res.status(200).json({
                ok: true,
                msg: `El semestre con el id ${params[0]} ha sido eliminado`,
                affectedRows: rowCount
            })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}