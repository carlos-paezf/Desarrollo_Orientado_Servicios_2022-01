import { Request, Response } from 'express'
import colors from 'colors'

import { products, services, users } from '../data'


class DAO {
    protected static getUsersDAO = async (req: Request, res: Response): Promise<any> => {
        return Promise.resolve(users)
            .then((response: any) => {
                console.log(colors.italic('Respuesta: '), response)
                res.status(200).json({ ok: true, response })
            })
            .catch((error: any) => {
                res.status(400).json({ ok: false })
                throw new Error(error);
            })
    }

    protected static getProductsDAO = async (req: Request, res: Response): Promise<any> => {
        return Promise.resolve(products)
            .then((response: any) => {
                console.log(colors.italic('Respuesta: '), response)
                res.status(200).json({ ok: true, response })
            })
            .catch((error: any) => {
                res.status(400).json({ ok: false })
                throw new Error(error)
            })
    }

    protected static getServicesDAO = async (req: Request, res: Response): Promise<any> => {
        return Promise.resolve(services)
            .then((response: any) => {
                console.log(colors.italic('Respuesta: '), response)
                res.status(200).json({ ok: true, response })
            })
            .catch((error: any) => {
                res.status(400).json({ ok: false })
                throw new Error(error)
            })
    }
}


export default DAO