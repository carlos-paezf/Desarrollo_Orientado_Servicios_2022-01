import e, { Request, Response } from 'express';
import { AccessDAO_Get } from '../../daos/access';
import { SQL_ACCESS_SELECT } from '../../repositories/access';


class AccessController_Get extends AccessDAO_Get {
    public getAccess = (req: Request, res: Response): void => {
        const { jwtPayload } = req.body
        console.log(jwtPayload)
        AccessController_Get.getAccess(SQL_ACCESS_SELECT.ALL, [], res)
    }
}


export const accessControllerGet = new AccessController_Get()