package com.example.springbatchassignment5.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springbatchassignment5.model.Text;

@Component
public class Processor implements ItemProcessor<Text,Text>{

	@Override
	public Text process(Text item) throws Exception {
		// TODO Auto-generated method stub
		if(item.getData().charAt(0)=='S') {
			return null;
		}
		return item;
	}

}
