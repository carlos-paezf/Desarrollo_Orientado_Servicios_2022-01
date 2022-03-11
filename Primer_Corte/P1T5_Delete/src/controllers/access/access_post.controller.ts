import { Request, Response } from 'express';
import { AccessDAO_Post } from '../../daos/access';
import { SQL_ACCESS_CREATE, SQL_ACCESS_SELECT } from '../../repositories/access';


class AccessController_Post extends AccessDAO_Post {

    public validateAccess = (req: Request, res: Response): void => {
        const { accessEmail, accessKey } = req.body
        AccessController_Post.validateAccess(SQL_ACCESS_SELECT.CONFIRM_ACCESS, [accessEmail, accessKey], res)
    }


    public postAccess = (req: Request, res: Response): Promise<any> | void => {
        const { accessEmail, accessKey } = req.body

        if (accessEmail.trim() === '' || accessKey.trim() === '') {
            return Promise.resolve(res.status(400).json({ ok: false, msg: 'No se pueden enviar campos vacíos' }))
        }

        AccessController_Post.postAccess(SQL_ACCESS_SELECT.CONFIRM_UNIQUE, SQL_ACCESS_CREATE.CREATE, [accessEmail, accessKey], res)
    }
}


export const accessControllerPost = new AccessController_Post()