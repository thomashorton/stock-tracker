import React from 'react';
import StockService from './StockService';

class StockComponent extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            stocks: []
        }
    }

    componentDidMount() {
        StockService.getStocks().then((response) => {
            this.setState({stocks: response.data})
        })
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
                                stock => 
                                <tr key = {stock.ticker}>
                                     <td> {stock.ticker}</td>   
                                     <td> {stock.currentPrice}</td>   
                                     <td> {stock.currency}</td>   
                                     <td> {stock.open}</td>
                                     <td> {stock.previousClose} </td>
                                </tr>
                            )
                        }

                    </tbody>
                </table>

            </div>

        )
    }

}
export default StockComponent