import { Request, Response } from 'express'

import { ProgramDAO_Update } from '../../daos/programs';
import { SQL_PROGRAMS_SELECT, SQL_PROGRAMS_UPDATE } from '../../repositories/programs';


class ProgramController_Update extends ProgramDAO_Update {
    /**
     * This is a function that will be called when the user request the `/update-program/:programId` endpoint. 
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     * @returns If the body is a empty string, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No se puede enviar un nombre vacío"
     * }
     * ```
     */
    public updateProgram = (req: Request, res: Response): void | Promise<any> => {
        const { programId } = req.params
        const { programName } = req.body
        if ( programName.trim() === '') {
            return Promise.resolve(res.status(400).json({ ok: false, msg: 'No se puede enviar un nombre vacío' }))
        }
        ProgramController_Update.updateProgram(
            SQL_PROGRAMS_SELECT.CONFIRM_ID, SQL_PROGRAMS_SELECT.CONFIRM_UNIQUE, 
            SQL_PROGRAMS_UPDATE.UPDATE_ONE, [programId, programName], 
            res
        )
    }
}


export const programControllerUpdate = new ProgramController_Update()