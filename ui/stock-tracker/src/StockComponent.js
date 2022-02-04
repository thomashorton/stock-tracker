import React from 'react';
import StockService from './StockService';

class StockComponent extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            currentTicker: "",
            stocks: []
        }
    }
    
    componentDidMount() {
        StockService.getStocks().then((response) => {
            this.setState({stocks: response.data})
        })
    }
      handleChange = idx => e => {
        const { name, value } = e.target;
        const stocks = [...this.state.stocks];
        stocks[idx] = {
          [name]: value
        };
        this.setState({
            stocks
        });
      };
      handleTickerChange = (event) => () => {
          console.log("updating ticker")
          console.log(event);
          console.log(this.state);
          this.setState({currentTicker:event.target.value})
      }
      async handleSubmit(event) {
        event.preventDefault();
        StockService.addStock(event.target.tickerEntry.value.toUpperCase()).then(response=> {
            if(response.ok) {
                StockService.refreshStocks();
                console.log(response);    
            }
        }).catch((error)=> {
            console.warn("invalid request");
            console.log(error);
        });
        window.location.reload(false);
      }
      handleRemoveSpecificRow = (idx) => () => {
        const stocks = [...this.state.stocks]
        var stockToRemove = stocks[idx]
        console.log(stockToRemove);
        StockService.deleteStock(stockToRemove.ticker);
        StockService.refreshStocks();
        console.log("refreshed stocks");
        window.location.reload(false);
      }

    render (){
        return (
            <div>
                <h1 className = "text-center"> Stocks List</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td> Ticker </td>
                            <td> Price </td>
                            <td> Currency </td>
                            <td> Open </td>
                            <td> Previous Close </td>
                        </tr>

                    </thead>
                    <tbody>
                        {
                            this.state.stocks.map(
                                (stock, idx) => 
                                <tr key = {stock.ticker}>
                                     <td> {stock.ticker}</td>   
                                     <td> {stock.currentPrice}</td>   
                                     <td> {stock.currency}</td>   
                                     <td> {stock.open}</td>
                                     <td> {stock.previousClose} </td>
                                     <td>
                                        <button
                                            className="btn btn-outline-danger btn-sm"
                                            onClick={this.handleRemoveSpecificRow(idx)}
                                        > Remove </button>
                                    </td>
                                    
                                </tr>
                            )
                        }

                    </tbody>
                </table>
                    <form onSubmit={this.handleSubmit}>
                        <label>
                        Ticker:
                        <input type="text" name="tickerEntry" defaultValue="test" onChange={this.handleTickerChange} />
                        </label>
                        <input type="submit" className="btn btn-primary" value="Submit" />
                    </form>
            </div>

        )
    }

}
export default StockComponent