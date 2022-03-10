import { Request, Response } from 'express';
import { PensumDAO_Get } from '../../daos/pensums';
import { SQL_PENSUM_SELECT } from '../../repositories/pensums';


class PensumController_Get extends PensumDAO_Get {
    /**
    * This is a function that will be called when the user request the `/pensums` endpoint.
    * @param {Request} req - Request - The request object that contains the request parameters.
    * @param {Response} res - Response - The response object that is passed back to the client.
    */
    public getPensums = (req: Request, res: Response): void => {
        PensumController_Get.getPensums(SQL_PENSUM_SELECT.ALL, [], res)
    }


    public getOnePensumById = (req: Request, res: Response): void => {
        const { pensumId } = req.params
        PensumController_Get.getOnePensumById(SQL_PENSUM_SELECT.CONFIRM_ID, SQL_PENSUM_SELECT.SELECT_ONE, [pensumId], res)
    }
}


export const pensumControllerGet = new PensumController_Get()