import jwt from 'jsonwebtoken'
import { config } from 'dotenv'
import { Request, Response, NextFunction } from 'express';
import { red } from 'colors';


class Validate {
    public validateToken = (req: Request, res: Response, next: NextFunction) => {
        try {
            const { authorization } = req.headers
            if (!authorization) return res.status(401).json({ ok: false, msg: 'Se debe añadir un JWT' })
            else {
                try {
                    const token = authorization.split(' ')[1] as string
                    const secretKey = process.env.SECRET_KEY
                    if (!secretKey) return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador - SIGNATURE' })
                    const data = jwt.verify(token, secretKey)
                    req.body.jwtPayload = data
                    next()
                } catch (error) {
                    return res.status(401).json({ ok: false, msg: 'JWT invalido' })
                }
            }
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}


export const validate = new Validate()