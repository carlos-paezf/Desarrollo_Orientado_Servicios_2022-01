import { Request, Response } from 'express'
import DAO from '../dao/DAO';


class Controller extends DAO {
    public getUsers = (req: Request, res: Response): Promise<any> => Controller.getUsersDAO(req, res)

    public getProducts = (req: Request, res: Response): Promise<any> => Controller.getProductsDAO(req, res)
    
    public getServices = (req: Request, res: Response): Promise<any> => Controller.getServicesDAO(req, res)
}


const controller = new Controller()
export default controller