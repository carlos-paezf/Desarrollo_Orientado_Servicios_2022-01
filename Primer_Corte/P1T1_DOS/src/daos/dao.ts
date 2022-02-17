import { Request, Response } from 'express'
import colors from 'colors';


class DAO {
    /**
     * It returns a JSON object with the professors and course name.
     * @param {Request} req - The request object.
     * @param {Response} res - Response
     * @returns The response is a JSON object with the following structure:
     * ```
     * {
     *     ok: true,
     *     response: {
     *         professors: ['Carlos Andrés', 'Harvey'],
     *         course: 'DOS'
     *     }
     * }
     * ```
     */
    protected static async getInfo(req: Request, res: Response): Promise<any> {
        const json = {
            professors: ['Carlos Andrés', 'Harvey'],
            course: 'DOS'
        }

        return Promise.resolve(json)
            .then((response: any) => {
                console.log(colors.italic.green('Respuesta: '), response)
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


export default DAO