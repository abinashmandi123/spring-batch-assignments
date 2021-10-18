package com.example.springbatchassignment5.config;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.example.springbatchassignment5.model.Text;



public class TextPreparedStatementSetter  implements ItemPreparedStatementSetter<Text>{

	@Override
	public void setValues(Text item, PreparedStatement ps) throws SQLException {
		
		ps.setString(1,item.getData());
		
	}

}
