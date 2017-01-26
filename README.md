# sctbrowser

sctbrowser is a small, simple and fast, Snomed CT Browser for viewing international and UK (and other countries?) clinical and drug extension RF2 data.

# Windows/PostgreSQL deployment

1. Download sctbrowser source:

        git checkout https://github.com/adrianwalker/sctbrowser.git

2. Build source with Maven:

        mvn -Pwindows/postgresql clean install

3. Download and install PostgreSQL:

    https://www.postgresql.org/download/

4. Create sctbrowser schema and user by executing:

        sctbrowser\src\main\resources\sql\postgresql\setup\setup.sql

5. Download and install Tomcat:

    http://tomcat.apache.org/download-80.cgi

6. Create data directory:

        C:\temp\data

7. Download RF2 dataset releases from TRUD:

    https://isd.hscic.gov.uk/trud3/user/guest/group/0/pack/26/subpack/103/releases
    https://isd.hscic.gov.uk/trud3/user/guest/group/0/pack/26/subpack/107/releases

8. Unzip RF2 archives to data directory:

        C:\temp\data

9. Start Tomcat

10. Copy built war file

    from:
        
        C:\Users\adwa1\Desktop\github\sctbrowser\target\sctbrowser.war 
    
    to: 
        
        &lt;TOMCAT_HOME&gt;\webapps\sctbrowser.war

11. Create load file:

        C:\temp\data\load

    containing the unzipped RF2 directory names, e.g.

        uk_sct2clsnap_22.0.0_20161005000001
        uk_sct2drsnap_22.4.0_20170111000001

    The sctbrowser watches for changes in the load file, creates required the PostgreSQL database tables and loads the provided RF2 data.

12. Monitor the log file for loading to finish:

        26-01-2017 21:38:52,575 INFO  [org.adrianwalker.terminology.sctbrowser.watch.DataDirectoryWatcher - lambda$watch$2] - processing file 'load'
        26-01-2017 21:38:52,613 INFO  [org.adrianwalker.terminology.sctbrowser.watch.CommandFileObserver - load] - starting load…
        ...
        26-01-2017 21:55:55,489 INFO  [org.adrianwalker.terminology.sctbrowser.watch.CommandFileObserver - load] - ...done

13. Browse to:

    http://localhost:8080/sctbrowser

