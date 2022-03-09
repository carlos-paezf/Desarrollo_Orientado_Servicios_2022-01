import { Request,  Response } from 'express'

import { ProgramDAO_Delete } from '../../daos/programs';
import { SQL_PROGRAMS_DELETE, SQL_PROGRAMS_SELECT } from '../../repositories/programs';


class ProgramController_Delete extends ProgramDAO_Delete {
    /**
     * This a function that will be called when the user request the `/programs/:programId` endpoint, with the verb DELETE
     * @param {Request} req - Request - The request object that contains the request parameters
     * @param {Response} res - Response  The response object that is passed back to the client.
     */
    public deleteOneProgramById = (req: Request, res: Response): void => {
        const { programId } = req.params
        ProgramController_Delete.deleteOneProgramById(SQL_PROGRAMS_SELECT.CONFIRM_ID, SQL_PROGRAMS_DELETE.DELETE, [programId], res)
    }
}


export const programControllerDelete = new ProgramController_Delete()