import { Request, Response } from 'express'


class DAO {
    protected static async getInfo(req: Request, res: Response): Promise<any> {
        const json = {
            professors: ['Carlos Andrés', 'Harvey'],
            course: 'DOS'
        }

        Promise.resolve(json)
            .then((response: any) => {
                console.log('Respuesta: ', response)
                res.status(200).json({
                    ok: true,
                    response
                })
            })
            .catch((error: any) => {
                res.status(400).json({ ok: false })
                throw new Error(`Error en getInfo(): ${error}`,)
            })
    }
}