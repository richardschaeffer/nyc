/*       COMMENTS, ASSUMPTIONS, AND OVERALL STRATEGY
 

In the rolled up table, each combination of activity_date, customer_id, store, and associate_id will have the set of aggregated values: 
average_trans, total_spend, and total_trans 

associate_id and store are not shared between the tables, so we can't compare with those aggregation levels.

Therefore, the lowest common 'grain' we can compare between our tables is activity_date and customer_id
and that is the aggregate level we will verify between the tables and report to the user anything that does not match.  

We pull the total spend and transaction count for each customer on each day for both tables and then report totals that don't match.  


COMMENTS
This script is not particularly efficient or fast.  
It would be much better with a starting date parameter and progress sequentially to break up the processing into pieces or coordinate running it on multiple machines/threads.  
Reporting on % completion could be useful
Currently this script proceeds in no particular order.


I have nothing to execute this script against so there are bound to be some syntax or logic errors
I've commented the important lines so you can follow my thinking even if the code has a mistake.  


Author:Richard Schaeffer 1/21/2019
This is for a Time Warner technical screen.  
*/ 


CREATE PROC qcCustomerData(  
                            @SourceDB 'ClotheInc'
                            )                        
AS 

BEGIN

/** declare variables **/  
DECLARE @activity_date varchar(30), @customer_id INT, @store VARCHAR(50), @associate_id INT, @agg_average_trans INT, @agg_total_spend INT, @agg_total_trans INT
DECLARE @transTotal INT, @transCount INT

/** Pull  totals at our chosen grain (customer and day) - only need sum and count **/
DECLARE Aggregate_Cursor CURSOR FOR 
	/** total and count must be summed to account for possible multiple stores/associate_id; grouped by date / customer_id as per comments **/
    SELECT DISTINCT activity_date , customer_id,  SUM(total_spend), SUM(total_trans) FROM cust_store_detail GROUP BY activity_date, customer_id


/** Open rolled up table's cursor and loop through each day / customer **/
OPEN Aggregate_Cursor 
/** Store initial values into variables **/
FETCH NEXT FROM Aggregate_Cursor INTO
    @activity_date, @customer_id, @store,  @agg_total_spend, @agg_total_trans

WHILE @@FETCH_STATUS = 0
BEGIN
	/** get spend total from non-rolled up table by summing together the amount of all transactions (cloth_cost) with the same customer and activity date **/
	@transTotal = SELECT SUM(cloth_cost) from cust_purchase_detail WHERE activity_date = @activity_date AND customer_id=@customer_id  GROUP BY activity_date, customer_id

	/** compare it to the aggregated total_spend from rolled-up table and report results to user - Raise errors if spend is under or overreported **/  
    IF @transTotal = @agg_total_spend
		/** do nothing, everything is good **/
    ELSE  
    	/** report amounts and customer_id/activity_date to user - these customers are missing transaction data from one table or the other **/  
    	RAISERROR('Mismatched Transaction Total for Customer %d on Date %s' ,0,1, @customer_id, @activity_date) WITH NOWAIT
    	RAISERROR('cust_store_detail (rolled up table) has total spend: %d' ,0,1, @agg_total_spend) WITH NOWAIT
    	RAISERROR('cust_purchase_detail has total spend: %d' ,0,1, @transTotal) WITH NOWAIT


	/** get count of transactions from non-rolled up table by pulling distinct transaction_id (there are known to be duplicates) for each customer and day **/
	@transCount = SELECT COUNT(DISTINCT trans_id) from cust_purchase_detail WHERE activity_date = @activity_date AND customer_id=@customer_id  

	/** compare it to the aggregated total_trans from rolled-up table and report results to user - Raise errors if transaction count is under or overreported **/  
    IF @transCount = @agg_total_trans
		/** do nothing, everything is good **/
    ELSE  
    	/** report amounts and customer_id/activity_date to user - these customers are missing transaction data from one table or the other **/  
    	RAISERROR('Mismatched Transaction Count for Customer %d on Date %s' ,0,1, @customer_id, @activity_date) WITH NOWAIT
    	RAISERROR('cust_store_detail (rolled up table) has transaction count: %d' ,0,1, @agg_total_trans) WITH NOWAIT
    	RAISERROR('cust_purchase_detail has transaction count : %d' ,0,1, @transCount) WITH NOWAIT


	/** pull next set of aggregate values **/  
    FETCH NEXT FROM Aggregate_Cursor INTO
    @activity_date, @customer_id, @store, @agg_total_spend, @agg_total_trans
END

/** close cursors **/
CLOSE Customer_Cursor
DEALLOCATE Customer_Cursor
