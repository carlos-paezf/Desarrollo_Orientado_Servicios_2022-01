import { Request, Response } from "express";
import { PensumDAO_Post } from "../../daos/pensums";
import { SQL_PENSUM_CREATE, SQL_PENSUM_SELECT } from "../../repositories/pensums";
import { SQL_PROGRAMS_SELECT } from "../../repositories/programs";


class PensumController_Post extends PensumDAO_Post {
    /**
     * This is a function that will be called when the user request the `/pensums/create-pensum` endpoint.
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public postPensum = (req: Request, res: Response): void => {
        const { programId, pensumName } = req.body
        const params = [programId, pensumName]
        PensumController_Post.postPensum(
            SQL_PROGRAMS_SELECT.CONFIRM_ID, SQL_PENSUM_SELECT.CONFIRM_UNIQUE, 
            SQL_PENSUM_CREATE.CREATE, params, 
            res
        )
    }
}


export const pensumControllerPost = new PensumController_Post()