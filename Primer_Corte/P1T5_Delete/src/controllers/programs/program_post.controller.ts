import { Request, Response } from 'express'

import { ProgramDAO_Post } from '../../daos/programs';
import { SQL_PROGRAMS_CREATE, SQL_PROGRAMS_SELECT } from '../../repositories/programs';


class ProgramController_Post extends ProgramDAO_Post {
    /**
     * This is a function that will be called when the user request the `/create-program` endpoint. 
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public postProgram = (req: Request, res: Response): void => {
        const { programName } = req.body
        ProgramController_Post.postProgram(SQL_PROGRAMS_SELECT.CONFIRM_UNIQUE, SQL_PROGRAMS_CREATE.CREATE, [programName], res)
    }
}


export const programControllerPost = new ProgramController_Post()