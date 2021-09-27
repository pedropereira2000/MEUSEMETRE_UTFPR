library(tidyverse)
library(readr)
library(lubridate)

bugs <- read_csv("bugs.csv")
summary(bugs)

plot1 <- bugs %>% ggplot(aes(x=fields.Custom.Environment)) +
  geom_bar()

plot1

plot2 <- bugs %>% ggplot(aes(x=fields.System.AreaPath)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))

plot2

plot3 <- bugs %>% ggplot(aes(x=fields.System.State)) +
  geom_bar()

pdf("plots.pdf")
print(plot1)     
print(plot2)     
print(plot3)     
dev.off()

bugs %>% 
  ggplot(aes(x=fields.Microsoft.VSTS.Common.Severity)) +
  geom_bar()

bugs %>% 
  ggplot(aes(x=fields.Microsoft.VSTS.Common.ResolvedReason)) +
  geom_bar()

######################
bugs$dateOfCreation <- as.Date(bugs$fields.System.CreatedDate) 

bugs$MonthOfCreation <- format(bugs$dateOfCreation, format = "%Y-%m")

bugs$week <- format(bugs$dateOfCreation, format = "%Y-%U")

pdf("plot-bugs_created_per_day.pdf")
plot1 <- bugs %>% ggplot(aes(x=dateOfCreation)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))
plot1
dev.off()

pdf("plot-bugs_created_per_month.pdf")
bugs %>% ggplot(aes(x=MonthOfCreation)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))
dev.off()

pdf("plot-bugs_created_per_week.pdf")
bugs %>% ggplot(aes(x=week)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))
dev.off()

######################
# distribution for the last 4 months

m1 <- bugs %>% 
  filter(month(dateOfCreation) == 12 & year(dateOfCreation) == 2020) %>% 
  ggplot(aes(x=fields.System.AreaPath)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))

m2 <- bugs %>% 
  filter(month(dateOfCreation) == 1 & year(dateOfCreation) == 2021) %>% 
  ggplot(aes(x=fields.System.AreaPath)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))

m3 <- bugs %>% 
  filter(month(dateOfCreation) == 2 & year(dateOfCreation) == 2021) %>% 
  ggplot(aes(x=fields.System.AreaPath)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))

m4 <- bugs %>% 
  filter(month(dateOfCreation) == 3 & year(dateOfCreation) == 2021) %>% 
  ggplot(aes(x=fields.System.AreaPath)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))

library("gridExtra")
grid.arrange(m1, m2, m3, m4, ncol = 2, nrow = 2)


###################
