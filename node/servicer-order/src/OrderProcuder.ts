import KafkaProducer from '../../common-kafka/src/KafkaProducer';

const main = async () => {
    const producer = new KafkaProducer('new_order');
    const messages = [
        {key: 'a', value: JSON.stringify({ id: 1, amount: 100.0})},
        {key: 'b', value: JSON.stringify({ id: 2, amount: 200.0})},
    ];
    producer.send(messages);
};

main();
