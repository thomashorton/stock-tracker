import axios from 'axios'
const STOCK_REST_API_URL = "http://localhost:8080/api/stocks/"

class StockService {

    getStocks() {
        return axios.get(STOCK_REST_API_URL);
    }

    addStock(ticker) {
        return axios.put(STOCK_REST_API_URL+ticker)
    }

    deleteStock(ticker){
        return axios.delete(STOCK_REST_API_URL+ticker)
    }

    refreshStocks(){
        return axios.post(STOCK_REST_API_URL+"refresh")
    }

}

export default new StockService();
