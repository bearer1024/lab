package uk.ac.le.cs.CO3098.bean;

import java.util.HashMap;

public class Question {
String question;
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getOption() {
	return option;
}
public void setOption(String option) {
	this.option = option;
}
public int getOptionCount() {
	return optionCount;
}
public void setOptionCount(int optionCount) {
	this.optionCount = optionCount;
}
public HashMap<String, Integer> getHashMap() {
	return hashMap;
}
public void setHashMap(HashMap<String, Integer> hashMap) {
	this.hashMap = hashMap;
}
String option;
int optionCount;
HashMap<String,Integer> hashMap = new HashMap<>();
}
