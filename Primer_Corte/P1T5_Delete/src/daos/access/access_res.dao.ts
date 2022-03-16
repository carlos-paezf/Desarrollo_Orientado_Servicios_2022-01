import { Response } from 'express';
import jwt from 'jsonwebtoken';

import 'dotenv/config'


export class AccessDAO_RES {
    public static process = async (data: any, res: Response): Promise<any> => {
        if (data !== null) {
            const token = jwt.sign({ id: data.accessId, rol: data.roleName }, process.env.SECRET_KEY!, { expiresIn: '2h' } )

            return res.status(200).json({
                ok: true,
                msg: 'Se ha generado un token de acceso',
                token
            })
        }
        return res.status(401).json({ ok: false, msg: `Error en el email o contraseña` })
    }
}