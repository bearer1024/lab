
  cd ~/lab/internetAndCloudComputing/assignment3/
  javac org/leicester/Hour.java 
  rm -rf Hour.jar
  jar cvf Hour.jar org 
  rm -rf ~/output
  hadoop jar Hour.jar org.leicester.Hour ~/input ~/output
  cat ~/output/part-r-00000 
