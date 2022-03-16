import { Request, Response } from 'express';
import { AccessDAO_Post } from "../../daos/access/access_post.dao";
import { SQL_ACCESS_SELECT } from "../../repositories/access/access_select.sql";


class AccessController_Post extends AccessDAO_Post {
    public access = (req: Request, res: Response): void => {
        const { email, password } = req.body
        const params = [email, password]
        AccessController_Post.validateAccess(SQL_ACCESS_SELECT.LOGIN, params, res)
    }
}


export const accessController_Post = new AccessController_Post()