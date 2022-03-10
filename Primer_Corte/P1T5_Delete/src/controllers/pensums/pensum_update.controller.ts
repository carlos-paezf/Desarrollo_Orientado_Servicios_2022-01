import { Request, Response } from "express";
import { PensumDAO_Update } from "../../daos/pensums";
import { SQL_PENSUM_SELECT, SQL_PENSUM_UPDATE } from "../../repositories/pensums";
import { SQL_PROGRAMS_SELECT } from "../../repositories/programs";


class PensumController_Update extends PensumDAO_Update {
    public updateOnePensumById = (req: Request, res: Response): Promise<any> | void => {
        const { pensumId } = req.params
        const { programId, pensumName } = req.body
        
        if (pensumName.trim() === '') {
            return Promise.resolve(res.status(400).json({ ok: false, msg: 'No se pueden enviar datos vacíos' }))
        }

        PensumController_Update.updateOnePensumById(
            SQL_PENSUM_SELECT.CONFIRM_ID, SQL_PROGRAMS_SELECT.CONFIRM_ID, SQL_PENSUM_SELECT.CONFIRM_UNIQUE,
            SQL_PENSUM_UPDATE.UPDATE, [pensumId, programId, pensumName],
            res
        )
    }
}


export const pensumControllerUpdate = new PensumController_Update()