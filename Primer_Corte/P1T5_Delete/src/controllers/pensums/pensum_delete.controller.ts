import { Request, Response } from "express";
import { PensumDAO_Delete } from "../../daos/pensums";
import { SQL_PENSUM_DELETE, SQL_PENSUM_SELECT } from "../../repositories/pensums";


class PensumController_Delete extends PensumDAO_Delete {
    public deleteOnePensumById = (req: Request, res: Response): void => {
        const { pensumId } = req.params
        PensumController_Delete.deleteOnePensumById(SQL_PENSUM_SELECT.CONFIRM_ID, SQL_PENSUM_DELETE.DELETE, [pensumId], res)
    }
}


export const pensumControllerDelete = new PensumController_Delete()