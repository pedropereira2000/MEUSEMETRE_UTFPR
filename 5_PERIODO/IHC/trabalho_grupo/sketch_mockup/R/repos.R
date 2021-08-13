library(tidyverse)
library(lubridate)

repos <- read_csv("repos.csv")
repos$dateOfLastCommit <- as.Date(repos$dateOfLastCommit) 

summary(repos)

nrow(repos %>% 
  filter(year(dateOfLastCommit) == 2021))

pdf("repos_perDateOfLastCommit.pdf")
repos %>% 
  ggplot(aes(x=dateOfLastCommit)) +
  geom_bar()

repos %>% 
  filter(year(dateOfLastCommit) == 2021) %>%
  ggplot(aes(x=dateOfLastCommit)) +
  geom_bar()
dev.off()
