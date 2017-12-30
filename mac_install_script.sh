#!/bin/bash

echo 'Starting env setup, this may take a while.'

#install brew
echo 'Installing brew...'
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

#make brew install dir at root for quick access
ln -s /usr/local/opt /opt

#install stuff
echo 'Installing software/tools (git,java,wildfly,etc.)...'
#iterm2
brew cask install iterm2
#git
brew install git
#java
brew cask install java
#maven
brew install maven
#wildfly
brew install wildfly-as
#intellijIDEA
brew cask install intellij-idea-ce

#put wildfly log in mac log folder
echo 'Changing wildfly log to: /var/log/wildfly/console.log'
mkdir /var/log/wildfly
ln -s /opt/wildfly-as/libexec/standalone/log/server.log /var/log/wildfly/console.log

#make dir for scripts
echo 'Making scripts folder'
mkdir ~/scripts

echo 'Creating scripts...'
#create service script - 'service wildfly start' rather than brew services wildfly-as start
printf '#!/bin/bash\n\napp=“”\n\n#services\nif [ "$1" = "wildfly" ]; then\napp=wildfly-as\nelif [ "$1" = "postgres" ]; then\napp=postgresql\nelse\necho “services is undefined - see ~/scripts/service.sh”\nexit 1\nfi\n\n#actions\nif [ "$2" == "start" ]; then\nbrew services start $app\nelif [ "$2" == "stop" ]; then\nbrew services stop $app\nelif [ "$2" == "restart" ]; then\nbrew services restart $app\nelse\necho “no action defined: see ~/scripts/service.sh”\nfi\n' > ~/scripts/service.sh
#create script to allow apt like commands for mac/brew - 'apt install iterm2' rather than 'brew cask install iterm2' 
printf '#!/bin/bash\n\ndeclare RESULT=$({ { brew $1 $2; } 2>&3 | sed “s/^/STDOUT: /“; } 3>&1 1>&2 | sed “s/^/STDERR: /“)\n\nif [[ $RESULT == *"Error"* ]]; then\necho "Could not find the in brew, checking brew-cask…”\nbrew cask $1 $2\nfi\n' > ~/scripts/apt.sh

#aliases
echo 'Adding aliases and config to bash_profile...'
printf '#colour\nexport LS_COLORS="rs=0:di=01;34:ln=01;36:mh=00:pi=40;33”\nexport CLICOLOR=1\nexport GREP_OPTIONS="--color=auto”\n\n#ALIASES\nalias apt="brew $1 $2 $3”\nalias apt="sh /Users/'$USER'/scripts/apt.sh" #brew install <package>\nalias apt-cache="brew search $1”\n\n#alias sudo="sudo “\nalias service="sh /Users/'$USER'/scripts/service.sh”\nalias ll="ls -la --color=always”\n' > ~/.bash_profile
source ~/.bash_profile

#test apt script by installing sublime
echo 'Installing sublime-text...'
apt install sublime-text