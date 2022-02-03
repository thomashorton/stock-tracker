import axios from 'axios'
const STOCK_REST_API_URL = "http://localhost:8080/api/stocks/"

class StockService {

    getStocks() {
        return axios.get(STOCK_REST_API_URL);
    }

}

export default new StockService();
