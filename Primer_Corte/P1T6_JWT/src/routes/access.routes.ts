import { Router } from 'express'
import { accessController_Post } from '../controllers/access/access_post.controller';


class AccessRouter {
    public accessRoutes: Router

    constructor() {
        this.accessRoutes = Router()
        this.config()
    }

    public config = () => {
        this.accessRoutes.post('/', accessController_Post.access)
    }
}


const accessRouter = new AccessRouter()
export default accessRouter.accessRoutes