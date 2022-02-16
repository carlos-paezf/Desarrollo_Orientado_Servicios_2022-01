import { Request, Response } from 'express'
import DAO from '../daos/dao'


class Controller extends DAO {
    /**
     * `attackMe` is a function that takes a `Request` and a `Response` as arguments
     * @param {Request} req - Request
     * @param {Response} res - Response - The response object.
     */
    public attackMe(req: Request, res: Response): void {
        Controller.getInfo(req, res)
    }
}


const controller = new Controller()
export default controller