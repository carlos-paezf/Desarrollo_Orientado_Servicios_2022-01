import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../../config/connection/connection_DB';


export class SemesterDAO_Update {
    protected static updateOneSemesterById = async (sqlConfirmId: string, sqlConfirmUnique: string, sqlUpdate: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount: amountId } = await connectionDB.pool.one(sqlConfirmId, params[0])
            if (amountId === '0') return res.status(400).json({ ok: false, msg: `No hay ningún semestre con el id ${params[0]}` })

            const { amount: amountSemester } = await connectionDB.pool.one(sqlConfirmUnique, params[1])
            if (amountSemester !== '0') return res.status(400).json({ ok: false, msg: 'No se pueden repetir los nombres de los semestres '})

            const { rowCount } = await connectionDB.pool.result(sqlUpdate, params)
            return res.status(200).json({ 
                ok: true,
                msg: `El semestre con el id ${params[0]} ha sido actualizado`,
                affectedRows: rowCount,
                idSemester: params[0]
            })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    } 
}