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
     * It creates the routes for the political party controller.
     */
    public config = (): void => {
        this.politicalPartyRoutes.get('/', politicalPartyController.getPoliticalParties)
        this.politicalPartyRoutes.post('/create', politicalPartyController.postPoliticalParty)
    }
}


const politicalPartyRoutes = new PoliticalPartyRoutes()
export default politicalPartyRoutes.politicalPartyRoutes