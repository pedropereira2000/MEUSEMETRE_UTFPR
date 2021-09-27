library(tidyverse)

c <- read_csv("commit-details-all.csv")
co <- read_csv("repo-overview-commits.csv")

table(c$repo)
unique(c$repo)

unique(c$author)
unique(c$committer)

c %>% 
  ggplot(aes(x=author)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))

sum(co$commits)
