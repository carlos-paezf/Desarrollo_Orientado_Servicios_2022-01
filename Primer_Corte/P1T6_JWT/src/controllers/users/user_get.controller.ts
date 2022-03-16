import { UserDAO_Get } from "../../daos/users/users_get.dao";
import { Request, Response } from 'express';
import { SQL_USERS_SELECT } from "../../repositories/users/users_select.sql";


class UserController_Get extends UserDAO_Get {
    public getUserAndLoginCountByID = (req: Request, res: Response): void => {
        const { id } = req.params
        UserController_Get.getUserAndLoginCountByID(SQL_USERS_SELECT.USER_ID, SQL_USERS_SELECT.COUNT_LOGIN, [id], res)
    }

    public getAllUsers = (req: Request, res: Response): void => {
        UserController_Get.getAllUsers(SQL_USERS_SELECT.USERS, [], res)
    }
}

export const userController_Get = new UserController_Get()