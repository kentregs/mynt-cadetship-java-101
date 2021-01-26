package javaLabs2;

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
					 + "timeStamp) VALUES (?,?,?,?,?,?,?)";

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
				   
				   ps.execute();
				   logger.log(Level.INFO, "\n" + 
			   					   		  "Inserted : {0}\n", entry.getMsisdn());
			}
			
			logger.log(Level.INFO, "\n\n>> DONE INSERTING SMS! <<\n\n");
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQLException", e);
		} finally {
//			try {
//			   if (con != null) {
//				   logger.info("\nDONE INSERTING SMS! \n");
//				   con.close();
//			   }
//			} catch (Exception e){
//				logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
//			}				
		}	 
	}

	@Override
	public void acquireSms() {
		// TODO Auto-generated method stub
		
	}
	
}
