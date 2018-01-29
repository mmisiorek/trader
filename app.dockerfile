FROM dordoka/play-framework

WORKDIR /home/play
RUN wget https://github.com/web3j/web3j/releases/download/v3.2.0/web3j-3.2.0.zip -O web3j.zip
RUN unzip web3j.zip
RUN rm -f web3j.zip
RUN mv web3j-3.2.0 .web3j
RUN sudo ln -s /home/play/.web3j/bin/web3j /usr/bin/web3j

RUN curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
RUN sudo apt-get install -y nodejs

RUN echo "deb http://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
RUN sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
RUN sudo apt-get update && yes | sudo apt-get install sbt mysql-client git

COPY --chown=play:play . /home/play/Code

RUN sudo npm install -g solc truffle
#RUN sudo mv /usr/local/bin/solcjs /usr/local/bin/solc
#RUN sudo ln -s /usr/bin/nodejs /usr/bin/node

WORKDIR /home/play/Code
RUN bash get-solidity.sh
#RUN sbt compile
#RUN sbt update

CMD ["/usr/bin/sbt", "run"]
