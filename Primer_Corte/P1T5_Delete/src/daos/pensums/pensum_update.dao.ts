import { Response } from 'express';
import { red } from 'colors';
import connectionDB from '../../config/connection/connection_DB';


export class PensumDAO_Update {
    protected static updateOnePensumById = async (sqlConfirmId: string, sqlConfirmProgram: string, sqlConfirmUnique: string, sqlUpdate: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount: amountId } = await connectionDB.pool.one(sqlConfirmId, params[0])
            if (amountId === '0') return res.status(400).json({ ok: false, msg: `No hay ningún pensum con el id ${params[0]}` })

            const { amount: amountPrograms } = await connectionDB.pool.one(sqlConfirmProgram, params[1])
            if (amountPrograms === '0') return res.status(400).json({ ok: false, msg: `No hay ningún programa con el id ${params[1]}` })

            const { amount: amountPensums } = await connectionDB.pool.one(sqlConfirmUnique, [params[1], params[2]])
            if (amountPensums !== '0') return res.status(400).json({ ok: false, msg: 'No se pueden repetir los campos de pensum' })

            const { rowCount } = await connectionDB.pool.result(sqlUpdate, params)
            return res.status(200).json({ 
                ok: true, 
                msg: `Se ha actualizado el pensum con el id ${params[0]}`,
                affectedRows: rowCount,
                idPensum: params[0]
            })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}