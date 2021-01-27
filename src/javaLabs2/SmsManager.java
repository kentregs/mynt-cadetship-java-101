package javaLabs2;

import java.util.Random;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class SmsManager implements ManageSms {
	final private static Logger logger = Logger.getLogger(DatabaseConnect.class.getName());
	static ArrayList<Sms> smsList = new ArrayList<>();		
	
	@Override
	public void insertSms(ArrayList<Sms> smsList, Connection con) {
		String query = "INSERT INTO sms "
					 + "(msisdn, "
					 + "recipient, "
					 + "sender, "
					 + "message, "
					 + "shortCode, "
					 + "transactionId, "
					 + "timeStamp, "
					 + "type, "
					 + "status) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			for(Sms entry : smsList) {
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1, entry.getMsisdn());
			    ps.setString(2, entry.getRecipient());
			    ps.setString(3, entry.getSender());
			    ps.setString(4, entry.getMessage());
			    ps.setString(5, entry.getShortCode());
			    ps.setInt(6, entry.getTransactionId());
			    ps.setTimestamp(7, entry.getTimestamp());
			    ps.setString(8, entry.getType());
			    ps.setString(9, entry.getStatus());
			   
			    ps.execute();
			    
//			    logger.log(Level.INFO, "\n" + 
//		   					   		   "Inserted : {0}\n", entry.getMsisdn());			    
			}
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQLException", e);
		} 
		
		logger.log(Level.INFO, "\nDONE INSERTING SMS! \n");
	}

	@Override
	public void acquireSms(Timestamp start, Timestamp end, Connection con) {
		String selectQuery = "SELECT * FROM sms_db.sms	\r\n"
						   + "WHERE timeStamp BETWEEN ? AND ?";		
		
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
        	PreparedStatement ps = con.prepareStatement(selectQuery);
    		ps.setTimestamp(1, start);
    		ps.setTimestamp(2, end);
    		
    		resultSet = ps.executeQuery();

            while(resultSet.next()){
            	result.add("\nidSMS: " + resultSet.getInt(1) 
                		+ "\nmsisdn: " + resultSet.getString(2)
                		+ "\nrecipient: " + resultSet.getString(3)
                		+ "\nsender: " + resultSet.getString(4)
                		+ "\nmessage: " + resultSet.getString(5)
                		+ "\nshortcode: " + resultSet.getString(6)
                		+ "\ntransaction id: " + resultSet.getInt(7)
                		+ "\ntimestamp: " + resultSet.getTimestamp(8) + "\n\n");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        logger.log(Level.INFO, "\nRetrieved SMS:\n{0}\n", result);        
	}

	@Override
	public void acquireSms(String stringValue, Connection con) {
		String selectQuery = "";
		
		if(stringValue.contains("PISO")) {
			selectQuery = "SELECT * FROM sms_db.sms	\r\n"
		   		   	    + "WHERE message = ?";
		}
		else {
			selectQuery = "SELECT * FROM sms_db.sms	\r\n"
	   		   	    	+ "WHERE msisdn = ?";
		}

		ResultSet resultSet = null;
		ArrayList<String> result = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ps.setString(1, stringValue);
			
			resultSet = ps.executeQuery();
		
		 while(resultSet.next()){
		 	result.add("\nidSMS: " + resultSet.getInt(1) 
		     		+ "\nmsisdn: " + resultSet.getString(2)
		     		+ "\nrecipient: " + resultSet.getString(3)
		     		+ "\nsender: " + resultSet.getString(4)
		     		+ "\nmessage: " + resultSet.getString(5)
		     		+ "\nshortcode: " + resultSet.getString(6)
		     		+ "\ntransaction id: " + resultSet.getInt(7)
		     		+ "\ntimestamp: " + resultSet.getTimestamp(8) + "\n\n");
		 }
		} catch (SQLException e) {
		 logger.log(Level.SEVERE, "SQLException", e);
		}
		
		logger.log(Level.INFO, "\nRetrieved SMS:\n{0}\n", result);
	}

	@Override
	public void acquireSms(Connection con) {
		String selectQuery = "SELECT * FROM sms_db.sms";		

		ResultSet resultSet = null;
		ArrayList<String> result = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(selectQuery);
			
			resultSet = ps.executeQuery();
		
		 while(resultSet.next()){
		 	result.add("\nidSMS: " + resultSet.getInt(1) 
		     		+ "\nmsisdn: " + resultSet.getString(2)
		     		+ "\nrecipient: " + resultSet.getString(3)
		     		+ "\nsender: " + resultSet.getString(4)
		     		+ "\nmessage: " + resultSet.getString(5)
		     		+ "\nshortcode: " + resultSet.getString(6)
		     		+ "\ntransaction id: " + resultSet.getInt(7)
		     		+ "\ntimestamp: " + resultSet.getTimestamp(8) + "\n\n");
		 }
		} catch (SQLException e) {
		 logger.log(Level.SEVERE, "SQLException", e);
		}
		
		logger.log(Level.INFO, "\nRetrieved SMS:\n{0}\n", result);
	}

	@Override
	public void sentSms() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acquireSms(Connection con, String... msisdn) {		
		for(String curr : msisdn) {
			String selectQuery = "SELECT * FROM sms_db.sms	\r\n"
							   + "WHERE msisdn = ?";		
	
			ResultSet resultSet = null;
			ArrayList<String> result = new ArrayList<>();
			
			try {
				PreparedStatement ps = con.prepareStatement(selectQuery);
				ps.setString(1, curr);
				
				resultSet = ps.executeQuery();
			
			 while(resultSet.next()){
			 	result.add("\nidSMS: " + resultSet.getInt(1) 
			     		+ "\nmsisdn: " + resultSet.getString(2)
			     		+ "\nrecipient: " + resultSet.getString(3)
			     		+ "\nsender: " + resultSet.getString(4)
			     		+ "\nmessage: " + resultSet.getString(5)
			     		+ "\nshortcode: " + resultSet.getString(6)
			     		+ "\ntransaction id: " + resultSet.getInt(7)
			     		+ "\ntimestamp: " + resultSet.getTimestamp(8) + "\n\n");
			 }
			} catch (SQLException e) {
			 logger.log(Level.SEVERE, "SQLException", e);
			}
			
			logger.log(Level.INFO, "\nRetrieved SMS:\n{0}\n", result);
		}
	}
	
}
