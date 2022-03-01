import { Router } from 'express'

import politicalPartyController from '../controllers/political_party.controller';


class PoliticalPartyRoutes {
    public politicalPartyRoutes: Router

    constructor() {
        this.politicalPartyRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated. 
     */
    public config = (): void => {
        this.politicalPartyRoutes.get('/', politicalPartyController.getPoliticalParties)
    }
}


const politicalPartyRoutes = new PoliticalPartyRoutes()
export default politicalPartyRoutes.politicalPartyRoutes