import { Router } from 'express';
import { accessControllerGet, accessControllerPost } from '../controllers/access';


class AccessRoutes {
    public accessRoutes: Router

    constructor() {
        this.accessRoutes = Router()
        this.config()
    }

    public config = () => {
        this.accessRoutes.get('/', accessControllerGet.getAccess)
        this.accessRoutes.post('/', accessControllerPost.validateAccess)
        this.accessRoutes.post('/create-access', accessControllerPost.postAccess)
    }
}


const accessRoutes = new AccessRoutes()
export default accessRoutes.accessRoutes