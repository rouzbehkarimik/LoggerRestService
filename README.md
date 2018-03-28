# LoggerRestService
Is a rest service which gets input as JSON and persist its indexed marked up data into ElasticSearch which makes it easy to use to centralize log and achieving much more from logs. like using the system input log to bill customers.



### instruction
use `sbt run` to initialize service.
to change logback.xml file directory use `-Dlogger.file` while executing `sbt run` or change its value in `build.sbt`.
it is recommended to use `ElasticSearchCleaner.sh` as a `cronjob` in order to avoid `ran out of space` Error.