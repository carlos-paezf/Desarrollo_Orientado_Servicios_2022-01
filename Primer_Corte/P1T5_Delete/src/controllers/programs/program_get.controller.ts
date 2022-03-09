import { Request, Response } from 'express'

import { ProgramDAO_Get } from '../../daos/programs';
import { SQL_PROGRAMS_SELECT } from '../../repositories/programs';



class ProgramController_Get extends ProgramDAO_Get {
    /**
     * This is a function that will be called when the user request the `/programs` endpoint.
     * It gets all programs from the database.
     * @param {Request} req - Request - the request object that contains the request parameters. 
     * @param {Response} res - Response - The response object that is passed back to the client. 
     */
    public getPrograms = (req: Request, res: Response): void => {
        ProgramController_Get.getPrograms(SQL_PROGRAMS_SELECT.ALL, [], res)
    }

    /**
     * This a function that will be called when the user request the `/programs/:programId` endpoint, with the verb GET
     * @param {Request} req - Request - The request object that contains the request parameters
     * @param {Response} res - Response  The response object that is passed back to the client.
     */
    public getOneProgramById = (req: Request, res: Response): void => {
        const { programId } = req.params
        ProgramController_Get.getOneProgramById(SQL_PROGRAMS_SELECT.CONFIRM_ID, SQL_PROGRAMS_SELECT.SELECT_ONE, [programId], res)
    }
}


export const programControllerGet = new ProgramController_Get()