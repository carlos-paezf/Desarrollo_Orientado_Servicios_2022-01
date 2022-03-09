import { Request, Response } from 'express'
import { red } from 'colors'
import connectionDB from '../../config/connection/connection_DB'


export class SubjectDAO_Delete {
    /**
     * Its delete one program by id.
     * @param {string} sqlConfirm - The SQL query to confirm that the program exists. 
     * @param {string} sqlDelete - The SQL query to delete the program.
     * @param {any} params - any
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "msg": "La materia con el id 0 ha sido eliminado",
     *      "affectedRows": 1
     * }
     * ```
     * In case the entered ID does not exists, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No existe ninguna materia con el id 0"
     * }
     * ```
     * In case the problem is with the backend, the response will be a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "Comuníquese con el Administrador"
     * }
     * ```
     */
    protected static deleteOneSubjectById = async (sqlConfirm: string, sqlDelete: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount } = await connectionDB.pool.one(sqlConfirm, params)
            if (amount === '0') return res.status(400).json({ ok: false, msg: `No existe ninguna materia con el id ${params[0]}` })
            const { rowCount } = await connectionDB.pool.result(sqlDelete, params)
            return res.status(200).json({ ok: true, msg: `La materia con el id ${params[0]} ha sido eliminada`, affectedRows: rowCount })
        } catch(error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}