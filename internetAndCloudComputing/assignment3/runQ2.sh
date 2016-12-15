
  cd ~/lab/internetAndCloudComputing/assignment3/
  javac org/leicester/District.java 
  rm -rf District.jar
  jar cvf District.jar org 
  rm -rf ~/output
  hadoop jar District.jar org.leicester.District ~/input ~/output
  cat ~/output/part-r-00000 
