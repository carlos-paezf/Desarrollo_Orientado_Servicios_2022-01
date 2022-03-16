import { Router } from 'express';
import { userController_Get } from '../controllers/users/user_get.controller';


class UserRoutes {
    public userRoutes: Router

    constructor() {
        this.userRoutes = Router()
        this.config()
    }

    public config = (): void => {
        this.userRoutes.get('/:id', userController_Get.getUserAndLoginCountByID)
        this.userRoutes.get('/', userController_Get.getAllUsers)
    }
}


const userRoutes = new UserRoutes()
export default userRoutes.userRoutes